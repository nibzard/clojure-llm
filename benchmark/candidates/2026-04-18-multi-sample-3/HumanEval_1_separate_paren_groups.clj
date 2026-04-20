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
         (reduce (fn [{:keys [depth current groups]} ch]
                   (let [new-depth (case ch
                                     \( (inc depth)
                                     \) (dec depth)
                                     depth)
                         new-current (str current ch)
                         new-groups (if (zero? new-depth)
                                      (conj groups new-current)
                                      groups)
                         reset-current (if (zero? new-depth) "" new-current)]
                     {:depth new-depth
                      :current reset-current
                      :groups new-groups}))
                 {:depth 0 :current "" :groups []})
         :groups))