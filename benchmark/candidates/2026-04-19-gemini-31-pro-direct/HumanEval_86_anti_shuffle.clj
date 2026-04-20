(defn anti_shuffle [s]
  (when s
    (clojure.string/join " "
                         (map #(apply str (sort %))
                              (clojure.string/split s #" " -1)))))