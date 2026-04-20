(defn words_string
  "You will be given a collection that may contain strings, nils, and nested collections.
  Return a flat vector of all non-blank words as strings, preserving left-to-right order.
  Treat commas and whitespace as separators inside strings, and ignore nil values entirely.

  Examples:
  >>> (words_string [\"Hi, my\" nil [\"name is\" [\"John\"]]])
  [\"Hi\" \"my\" \"name\" \"is\" \"John\"]

  >>> (words_string [nil [\"One, two\" [\"three, four\"]] \"five\"])
  [\"One\" \"two\" \"three\" \"four\" \"five\"]"
  [s]
  (->> s
       (tree-seq coll? seq)
       (remove coll?)
       (keep identity)
       (mapcat #(clojure.string/split % #"[,\s]+"))
       (remove clojure.string/blank?)
       vec))