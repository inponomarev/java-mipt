# -*- encoding: utf-8 -*-
# stub: asciidoctor-revealjs 4.1.0 ruby lib

Gem::Specification.new do |s|
  s.name = "asciidoctor-revealjs".freeze
  s.version = "4.1.0".freeze

  s.required_rubygems_version = Gem::Requirement.new(">= 0".freeze) if s.respond_to? :required_rubygems_version=
  s.require_paths = ["lib".freeze]
  s.authors = ["Olivier Bilodeau".freeze]
  s.date = "2020-12-19"
  s.description = "Converts AsciiDoc documents into HTML5 presentations designed to be executed by the reveal.js presentation framework.".freeze
  s.email = ["olivier@bottomlesspit.org".freeze]
  s.executables = ["asciidoctor-revealjs".freeze]
  s.extra_rdoc_files = ["README.adoc".freeze, "LICENSE.adoc".freeze]
  s.files = ["LICENSE.adoc".freeze, "README.adoc".freeze, "bin/asciidoctor-revealjs".freeze]
  s.homepage = "https://github.com/asciidoctor/asciidoctor-reveal.js".freeze
  s.licenses = ["MIT".freeze]
  s.rubygems_version = "3.1.4".freeze
  s.summary = "A reveal.js converter for Asciidoctor. Write your slides in AsciiDoc!".freeze

  s.installed_by_version = "3.6.3".freeze

  s.specification_version = 4

  s.add_runtime_dependency(%q<asciidoctor>.freeze, [">= 2.0.0".freeze, "< 3.0.0".freeze])
  s.add_runtime_dependency(%q<thread_safe>.freeze, ["~> 0.3.5".freeze])
  s.add_runtime_dependency(%q<concurrent-ruby>.freeze, ["~> 1.0".freeze])
  s.add_development_dependency(%q<rake>.freeze, ["~> 13.0.0".freeze])
  s.add_development_dependency(%q<asciidoctor-doctest>.freeze, ["= 2.0.0.beta.7".freeze])
  s.add_development_dependency(%q<minitest>.freeze, ["~> 5.14".freeze])
  s.add_development_dependency(%q<pry>.freeze, ["~> 0.12.0".freeze])
  s.add_development_dependency(%q<irb>.freeze, [">= 0".freeze])
  s.add_development_dependency(%q<pry-byebug>.freeze, [">= 0".freeze])
  s.add_development_dependency(%q<pygments.rb>.freeze, [">= 0".freeze])
  s.add_development_dependency(%q<colorize>.freeze, [">= 0".freeze])
  s.add_development_dependency(%q<asciidoctor-templates-compiler>.freeze, ["~> 0.6.0".freeze])
  s.add_development_dependency(%q<slim>.freeze, ["~> 3.0.6".freeze])
  s.add_development_dependency(%q<slim-htag>.freeze, ["~> 0.1.0".freeze])
  s.add_development_dependency(%q<rouge>.freeze, [">= 0".freeze])
end
