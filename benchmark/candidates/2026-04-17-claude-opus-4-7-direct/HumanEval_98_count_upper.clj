(defn count_upper
  "Given a string s, count the number of uppercase vowels in even indices."
  [s]
  (when (string? s)
    (->> s
         (map-indexed vector)
         (filter (fn [[idx char]]
                   (and (even? idx)
                        (Character/isUpperCase char)
                        (#{\A \E \I \O \U} char))))
         count)))