(defn digit-distance-strings
  "Return the sum of absolute per-position differences between two numeric strings.
  If the strings have different lengths, compare only up to the shorter length.
  Examples:
  (digit-distance-strings \"314\" \"159\") => 13
  (digit-distance-strings \"12\" \"9876\") => 8"
  [s1 s2]
  (reduce +
          (map (fn [a b]
                 (Math/abs ^long (- (Character/digit ^char a 10)
                                    (Character/digit ^char b 10))))
               s1 s2)))