# -*- encoding: utf-8 -*-
# stub: rouge 5.1.0 ruby lib

Gem::Specification.new do |s|
  s.name = "rouge".freeze
  s.version = "5.1.0".freeze

  s.required_rubygems_version = Gem::Requirement.new(">= 0".freeze) if s.respond_to? :required_rubygems_version=
  s.metadata = { "bug_tracker_uri" => "https://github.com/rouge-ruby/rouge/issues", "changelog_uri" => "https://github.com/rouge-ruby/rouge/blob/main/CHANGELOG.md", "documentation_uri" => "https://rouge-ruby.github.io/docs/", "rubygems_mfa_required" => "true", "source_code_uri" => "https://github.com/rouge-ruby/rouge" } if s.respond_to? :metadata=
  s.require_paths = ["lib".freeze]
  s.authors = ["Jeanine Adkisson".freeze]
  s.date = "1980-01-02"
  s.description = "Rouge aims to a be a simple, easy-to-extend drop-in replacement for pygments.".freeze
  s.email = ["jneen@jneen.net".freeze]
  s.executables = ["rougify".freeze]
  s.files = ["bin/rougify".freeze]
  s.homepage = "http://rouge.jneen.net/".freeze
  s.licenses = ["MIT".freeze, "BSD-2-Clause".freeze]
  s.required_ruby_version = Gem::Requirement.new(">= 3.0".freeze)
  s.rubygems_version = "3.6.9".freeze
  s.summary = "A pure-ruby colorizer based on pygments".freeze

  s.installed_by_version = "3.6.3".freeze

  s.specification_version = 4

  s.add_runtime_dependency(%q<strscan>.freeze, ["~> 3.1".freeze])
end
