(defn anti_shuffle
  [s]
  (if (nil? s)
    nil
    (clojure.string/join
      (map #(apply str (sort %))
           (re-seq #"\S+|\s+" s)))))