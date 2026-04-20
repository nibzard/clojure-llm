(defn separate_paren_groups
  " Input to this function is a string containing multiple groups of nested parentheses. Your goal is to
  separate those group into separate strings and return the list of those.
  Separate groups are balanced (each open brace is properly closed) and not nested within each other
  Ignore any spaces in the input string.
  >>> (separate_paren_groups \"( ) (( )) (( )( ))\")
  [\"()\" \"(())\" \"(()())\"]"
  [paren_string]
  (let [chars (remove #{\space} paren_string)]
    (loop [cs chars
           depth 0
           current []
           groups []]
      (if (empty? cs)
        groups
        (let [c (first cs)
              current' (conj current c)
              depth' (case c
                       \( (inc depth)
                       \) (dec depth)
                       depth)
              groups' (if (zero? depth')
                        (conj groups (apply str current'))
                        groups)]
          (recur (rest cs)
                 depth'
                 (if (zero? depth') [] current')
                 groups'))))))