(defn all_Characters_Same
  "	Write a cljthon function to check whether all the characters are same or not."
  [s]
  (or (nil? s)
      (empty? s)
      (apply = s)))