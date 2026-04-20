(defn all_Characters_Same
  "Write a cljthon function to check whether all the characters are same or not."
  [s]
  (if (empty? s)
    true
    (let [first-char (first s)]
      (every? #(= first-char %) s))))