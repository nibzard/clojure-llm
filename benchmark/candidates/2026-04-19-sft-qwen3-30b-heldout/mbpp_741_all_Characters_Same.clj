(defn all_Characters_Same
  "	Write a cljthon function to check whether all the characters are same or not."
  [s]
  (let [ch (first (seq s))]
    (or (nil? ch)
        (every? #(= % ch) s))))