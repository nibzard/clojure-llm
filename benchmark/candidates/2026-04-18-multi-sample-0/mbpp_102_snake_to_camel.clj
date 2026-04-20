(defn snake_to_camel
  "	Write a function to convert a snake case string to camel case string."
  [word]
  (let [s (or word "")]
    (if (empty? s)
      s
      (->> (clojure.string/split s #"_")
           (map-indexed (fn [idx part]
                          (if (zero? idx)
                            part
                            (clojure.string/capitalize part))))
           (apply str)))))