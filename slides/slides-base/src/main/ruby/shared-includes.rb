# frozen_string_literal: true

# Lets any deck pull in a shared snippet with a bare include, e.g.
#
#   :x-post-url: https://x.com/user/status/123
#   include::x-post.adoc[]
#
# Asciidoctor resolves includes relative to the including file only, so without this
# every deck would need the relative path to slides-base. A target with no directory
# component that is missing next to the including file is looked up in
# slides-base/src/main/asciidoc instead. A deck-local file with the same name wins.
require 'asciidoctor'
require 'asciidoctor/extensions'

module SharedIncludes
  SHARED_DIR = File.expand_path('../asciidoc', __dir__)

  class Processor < Asciidoctor::Extensions::IncludeProcessor
    def handles? target
      !target.include?('/') && File.file?(File.join(SHARED_DIR, target))
    end

    def process _doc, reader, target, attributes
      local = File.join(reader.dir, target)
      path = File.file?(local) ? local : File.join(SHARED_DIR, target)
      content = File.read(path, mode: 'r:UTF-8')
      reader.push_include content, path, target, 1, attributes
      reader
    end
  end
end

Asciidoctor::Extensions.register do
  include_processor SharedIncludes::Processor
end
