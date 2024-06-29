#!/bin/bash
for i in {01..14}
do
   pushd slides${i}/src/main/asciidoc
   txt2po -P lecture.adoc lecture.pot
   msgmerge -U lecture${i}-en.po lecture.pot
   popd
done
echo 'Po files update done'
