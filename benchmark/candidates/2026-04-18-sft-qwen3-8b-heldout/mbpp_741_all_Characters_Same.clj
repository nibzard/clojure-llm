(defn all_Characters_Same
  "	Write a cljthon function to check whether all the characters are same or not."
  [s]
  (let [s (or s "")]
    (or (empty? s)
        (every? #(= % (first s)) s))))