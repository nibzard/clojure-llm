(defn all_Characters_Same
  "	Write a cljthon function to check whether all the characters are same or not."
  [s]
  (cond
    (nil? s) true
    (empty? s) true
    :else (apply = s)))