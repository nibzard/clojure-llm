(defn multiple_to_single
  "	Write a function to join a list of multiple integers into a single integer."
  [L]
  (let [parts (map #(str (bigint %)) (or L []))
        s (apply str parts)]
    (if (empty? s)
      0
      (bigint s))))