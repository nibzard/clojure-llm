(defn tuple_str_int
  "	Write a function to convert vector string to integer vector."
  [test_str]
  (cond
    (nil? test_str) []
    (string? test_str)
    (let [s (clojure.string/trim test_str)]
      (if (empty? s)
        []
        (->> (re-seq #"-?\d+" s)
             (mapv #(Integer/parseInt %)))))
    (sequential? test_str)
    (->> test_str
         (mapv (fn [x]
                 (cond
                   (integer? x) x
                   (number? x) (int x)
                   (string? x) (Integer/parseInt (clojure.string/trim x))
                   :else (Integer/parseInt (str x))))))
    :else []))