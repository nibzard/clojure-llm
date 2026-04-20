(defn separate_paren_groups
  " Input to this function is a string containing multiple groups of nested parentheses. Your goal is to
  separate those group into separate strings and return the list of those.
  Separate groups are balanced (each open brace is properly closed) and not nested within each other
  Ignore any spaces in the input string.
  >>> (separate_paren_groups "( ) (( )) (( )( ))")
  [\"()\" \"(())\" \"(()())\"]"
  [paren_string]
  (let [s (or paren_string "")]
    (->> s
         (remove #(Character/isWhitespace ^char %))
         (reduce (fn [[groups current depth] ch]
                   (let [new-current (str current ch)
                         new-depth (case ch
                                     \( (inc depth)
                                     \) (dec depth)
                                     depth)]
                     (if (zero? new-depth)
                       [(conj groups new-current) "" 0]
                       [groups new-current new-depth])))
                 [[] "" 0])
         first))