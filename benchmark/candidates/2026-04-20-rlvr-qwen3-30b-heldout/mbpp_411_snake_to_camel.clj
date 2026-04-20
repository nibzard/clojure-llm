(defn snake_to_camel
  "	Write a function to convert the given snake case string to camel case string."
  [word]
  (let [s (or word "")]
    (if (empty? s)
      ""
      (apply str
             (mapcat (fn [[idx ch]]
                       (if (= ch \_)
                         (list (Character/toUpperCase (char (nth s (inc idx)))))
                         [ch]))
                     (map-indexed vector s))))))