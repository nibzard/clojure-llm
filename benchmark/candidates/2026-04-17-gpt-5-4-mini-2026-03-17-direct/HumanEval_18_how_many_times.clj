(defn how_many_times
  " Find how many times a given substring can be found in the original string. Count overlaping cases.
  >>> (how_many_times \"\" \"a\")
  0
  >>> (how_many_times \"aaa\" \"a\")
  3
  >>> (how_many_times \"aaaa\" \"aa\")
  3"
  [string substring]
  (if (or (nil? string) (nil? substring) (empty? substring) (> (count substring) (count string)))
    0
    (count (re-seq (re-pattern (java.util.regex.Pattern/quote substring)) string))))