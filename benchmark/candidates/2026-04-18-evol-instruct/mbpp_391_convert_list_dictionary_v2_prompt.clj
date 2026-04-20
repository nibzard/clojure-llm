(defn zip-to-deep-map
  "Convert a collection of key sequences and a collection of values into a nested map.

  Each key sequence represents a path. The value is associated with the deepest key.

  Examples:
  (zip-to-deep-map [[:a :b] [:a :c] [:d]] [1 2 3])
  => {:a {:b 1, :c 2}, :d 3}

  Works with nil values and ignores extra keys if there are more paths than values.
  Returns {} when either input is empty."
  [paths values])