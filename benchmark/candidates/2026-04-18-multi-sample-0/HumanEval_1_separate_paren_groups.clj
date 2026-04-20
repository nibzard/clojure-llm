(defn separate_paren_groups
  " Input to this function is a string containing multiple groups of nested parentheses. Your goal is to
  separate those group into separate strings and return the list of those.
  Separate groups are balanced (each open brace is properly closed) and not nested within each other
  Ignore any spaces in the input string.
  >>> (separate_paren_groups \"( ) (( )) (( )( ))\")
  [\"()\" \"(())\" \"(()())\"]"
  [paren_string]
  (let [s (apply str (remove #(Character/isWhitespace %) (or paren_string "")))]
    (->> s
         (reduce (fn [{:keys [depth current groups]} ch]
                   (let [new-depth (+ depth (if (= ch \() 1 -1))
                         new-current (str current ch)]
                     (if (zero? new-depth)
                       {:depth 0 :current "" :groups (conj groups new-current)}
                       {:depth new-depth :current new-current :groups groups})))
                 {:depth 0 :current "" :groups []})
         :groups)))