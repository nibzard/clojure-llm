(defn upper-ctr
  "Return the number of uppercase characters in a collection of strings.

  Accepts nil, strings, or any seqable collection of strings. Counts uppercase
  characters across all non-nil items.

  Examples:
  (upper-ctr [\"AbC\" \"deF\"]) ;=> 3
  (upper-ctr nil)             ;=> 0
  (upper-ctr [\"hello\" nil \"WORLD\"]) ;=> 5"
  [xs]
  (if (nil? xs)
    0
    (reduce
      (fn [acc s]
        (if (nil? s)
          acc
          (+ acc (count (filter #(Character/isUpperCase ^char %) s)))))
      0
      (if (string? xs) [xs] xs))))