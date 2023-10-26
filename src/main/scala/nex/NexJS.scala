package nex

object NexJS {
  private val lang = Map(
    "scala" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/scala.min.js\"></script>",
    "java" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/java.min.js\"></script>",
    "python" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/python.min.js\"></script>",
    "javascript" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/javascript.min.js\"></script>",
    "csharp" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/csharp.min.js\"></script>",
    "php" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/php.min.js\"></script>",
    "go" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/go.min.js\"></script>",
    "c" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/c.min.js\"></script>",
    "cpp" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/cpp.min.js\"></script>",
    "ruby" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/ruby.min.js\"></script>",
    "perl" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/perl.min.js\"></script>",
    "swift" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/swift.min.js\"></script>",
    "rust" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/rust.min.js\"></script>",
    "kotlin" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/kotlin.min.js\"></script>",
    "scala" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/scala.min.js\"></script>",
    "groovy" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/groovy.min.js\"></script>",
    "haskell" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/haskell.min.js\"></script>",
    "sql" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/sql.min.js\"></script>",
    "vb" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/vb.min.js\"></script>",
    "matlab" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/matlab.min.js\"></script>",
    "lua" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/lua.min.js\"></script>",
    "dart" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/dart.min.js\"></script>",
    "r" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/r.min.js\"></script>",
    "fsharp" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/fsharp.min.js\"></script>",
    "elixir" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/elixir.min.js\"></script>",
    "erlang" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/erlang.min.js\"></script>",
    "shell" -> "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.7.2/languages/shell.min.js\"></script>"
  )

  def getScriptTags(usedLanguages: Set[String]): String = {
    usedLanguages.map(lang).mkString("\n")
  }
}
