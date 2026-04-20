(defn dedupe-consecutive
  "Return a vector with consecutive duplicate elements removed, preserving order.

  Works with any sequential collection, including nil.

  Examples:
  >>> (dedupe-consecutive [1 1 2 2 2 3 1 1])
  [1 2 3 1]
  >>> (dedupe-consecutive [:a :a :b :b :c])
  [:a :b :c]
  >>> (dedupe-consecutive nil)
  []
  >>> (dedupe-consecutive [])
  []"
  [xs])