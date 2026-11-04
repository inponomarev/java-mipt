#!/bin/bash
for i in {01..14}
do
   pushd slides${i}/src/main/asciidoc
   po2txt -t lecture.adoc lecture${i}-en.po lecture-en.adoc
   popd
done
echo 'English adoc generation done'
