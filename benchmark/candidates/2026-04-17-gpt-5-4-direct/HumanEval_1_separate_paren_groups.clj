(defn separate_paren_groups
  " Input to this function is a string containing multiple groups of nested parentheses. Your goal is to
  separate those group into separate strings and return the list of those.
  Separate groups are balanced (each open brace is properly closed) and not nested within each other
  Ignore any spaces in the input string.
  >>> (separate_paren_groups \"( ) (( )) (( )( ))\")
  [\"()\" \"(())\" \"(()())\"]"
  [paren_string]
  (let [s (or paren_string "")]
    (->> s
         (remove #(Character/isWhitespace ^char %))
         (reduce (fn [{:keys [groups current depth]} ch]
                   (let [new-current (conj current ch)
                         new-depth (case ch
                                     \( (inc depth)
                                     \) (dec depth)
                                     depth)]
                     (if (zero? new-depth)
                       {:groups (conj groups (apply str new-current))
                        :current []
                        :depth 0}
                       {:groups groups
                        :current new-current
                        :depth new-depth})))
                 {:groups [] :current [] :depth 0})
         :groups)))