package nex

final class NexPlain(val source : String)extends Dox(source) {
  override val EXT: String = ".txt"
}

object NexPlain{
  def apply(source : String) = new NexPlain(source)
}