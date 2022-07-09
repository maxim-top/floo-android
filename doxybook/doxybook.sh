rm -rf xml
rm -rf xml_input
rm -rf gitbook
rm -rf gitbook_for_publish
if [ $1 == clean ]; then
    exit 
fi
mkdir xml_input
mkdir gitbook
mkdir gitbook_for_publish
doxygen
cp xml/index.xml xml_input/
cp xml/class*.xml xml_input/
doxybook2 -i xml_input -o gitbook --summary-output gitbook/SUMMARY.md
cp gitbook/index_classes.md gitbook_for_publish/
cp -r gitbook/Classes gitbook_for_publish/
find gitbook_for_publish -name "class*.md" -exec sed -i .bak 's#](Classes/#](#g' {} \;
sed -i .bak '/SwigNext/d' gitbook_for_publish/index_classes.md 
sed -i .bak '/encoder.md/d' gitbook_for_publish/index_classes.md 
sed -i .bak '/decoder.md/d' gitbook_for_publish/index_classes.md 
mv gitbook_for_publish/index_classes.md gitbook_for_publish/SUMMARY.md
find gitbook_for_publish -name "*swig_next.md" -exec rm -rf {} \;
find gitbook_for_publish -name "*encoder.md" -exec rm -rf {} \;
find gitbook_for_publish -name "*decoder.md" -exec rm -rf {} \;
find gitbook_for_publish -name "*.bak" -exec rm -rf {} \;
rm -rf xml
rm -rf xml_input
rm -rf gitbook
