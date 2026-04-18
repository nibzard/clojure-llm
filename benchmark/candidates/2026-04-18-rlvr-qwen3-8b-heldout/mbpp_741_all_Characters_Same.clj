(defn all_Characters_Same
  "	Write a cljthon function to check whether all the characters are same or not."
  [s]
  (let [xs (seq s)]
    (or (nil? xs)
        (apply = xs))))