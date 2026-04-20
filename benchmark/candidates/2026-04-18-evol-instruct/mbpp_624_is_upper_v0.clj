(defn last-upper
  "Return the last uppercase character in a string, or nil if there is none.

  Examples:
  (last-upper \"cljTHON\") ;=> \\N
  (last-upper \"abc\")     ;=> nil
  (last-upper nil)        ;=> nil"
  [s]
  (when s
    (last (filter #(Character/isUpperCase ^char %) s))))