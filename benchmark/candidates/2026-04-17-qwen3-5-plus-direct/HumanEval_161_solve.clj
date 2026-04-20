(defn solve
  [s]
  (if (some #(Character/isLetter %) s)
    (apply str (map #(if (Character/isLetter %)
                       (if (Character/isLowerCase %)
                         (Character/toUpperCase %)
                         (Character/toLowerCase %))
                       %) s))
    (apply str (reverse s))))