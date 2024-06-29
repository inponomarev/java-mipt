#!/bin/bash
cd "$(dirname "$0")"
echo "Running spellchecking..."
result=$(
for i in {01..14}
do
   pushd slides${i}/src/main/asciidoc
   cat lecture-en.adoc | sed "s/-/ /g" | aspell --run-together --run-together-limit=5 --master=en --personal=./allowed_spelling_words.txt list;
   popd
done | sort | uniq)

if [[ $result ]]; then
	echo "The following words are probably misspelled:"
	echo "-----"
	echo $result
	echo "-----"
	echo "Please correct the spelling or add the words above to the local dictionary."
	exit 1;
else
	echo "Spellchecking complete, no misspelled words found."
fi
