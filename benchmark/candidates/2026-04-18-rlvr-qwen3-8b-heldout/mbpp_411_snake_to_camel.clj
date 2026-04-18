(defn snake_to_camel
  "	Write a function to convert the given snake case string to camel case string."
  [word]
  (when (some? word)
    (let [s (or word "")]
      (clojure.string/capitalize
       (clojure.string/replace s #"_\w" #(apply str (map #(Character/toUpperCase ^char %) %)))))))