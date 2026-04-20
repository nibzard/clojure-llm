(defn all_Characters_Same
  "	Write a cljthon function to check whether all the characters are same or not."
  [s]
  (let [s (str s)]
    (or (empty? s)
        (apply = s))))