(defn multiple_to_single
  "	Write a function to join a list of multiple integers into a single integer."
  [L]
  (let [s (apply str (map #(str (Math/abs (long %))) L))]
    (if (seq s)
      (Long/parseLong s)
      0)))