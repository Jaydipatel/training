import xml.etree.ElementTree as ET
from xml.dom import minidom
import openpyxl
import os

# Open the Excel file
wb = openpyxl.load_workbook(os.path.join(os.getcwd(), 'data.xlsx'))
ws = wb.active

# Create the root element with namespace prefix declaration
root = ET.Element('item')
root.set('sling:test', 'nt:unstructured')
root.set('xmlns:jcr', 'http://www.jcp.org/jcr/1.0')
root.set('xmlns:sling', 'http://www.sling.apache.org/jcr/sling/1.0')

# Loop through the rows in the Excel file and create elements for each row
for row in ws.iter_rows(min_row=2, values_only=True):
    # Extract the data from the row
    name, primary_type, resource_type = row

    # Create the element
    elem = ET.Element(name)
    elem.set('jcr:primaryType', 'nt:unstructured')
    elem.set('sling:resourceType',
    'granite/ui/components/coral/foundation/form/textfield' if resource_type == 'plain text'
    else 'dam/cfm/admin/components/authoring/contenteditor/multieditor' if resource_type == 'RTE'
    else 'dam/cfm/models/editor/components/contentreference' if resource_type == 'selector'
    else resource_type)
    elem.set('metaType',
    'text-single' if resource_type == 'plain text'
    else 'text-multi' if resource_type == 'RTE'
    else 'reference' if resource_type == 'selector'
    else resource_type)
    elem.set('fieldLabel',primary_type)
    elem.set('name', name)
    elem.set('renderReadOnly', 'false')
    elem.set('valueType', 'string')
    elem.set('showEmptyInReadOnly', 'true')

    # Add the element to the root element
    root.append(elem)

# Generate the XML string with pretty printing
xml_string = ET.tostring(root, encoding='utf-8')
xml_pretty = minidom.parseString(xml_string).toprettyxml(indent='  ')

# Write the XML to a file
with open('output.xml', 'w') as f:
    f.write(xml_pretty)
