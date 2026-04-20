(defn sum-uppercase-name-lengths
  "Return the total length of all strings in the collection after removing any names that start with a lowercase letter.

  The function must:
  - work with vectors, lists, or lazy sequences
  - treat nil as an empty collection
  - ignore non-string values

  Examples:
  (sum-uppercase-name-lengths [\"Alice\" \"bob\" \"Carol\"]) ;=> 10
  (sum-uppercase-name-lengths nil) ;=> 0
  (sum-uppercase-name-lengths '(\"Ann\" nil 42 \"ed\" \"Zoe\")) ;=> 6"
  [names]
  (transduce
   (comp
    (filter string?)
    (filter #(or (empty? %) (Character/isUpperCase ^char (first %))))
    (map count))
   +
   0
   (or names [])))