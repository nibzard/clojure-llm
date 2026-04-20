(defn compare_one
  [a b]
  (let [parse-num (fn [x]
                    (cond
                      (number? x) (double x)
                      (string? x) (Double/parseDouble (clojure.string/replace x "," "."))
                      :else nil))
        na (parse-num a)
        nb (parse-num b)]
    (cond
      (= na nb) nil
      (> na nb) a
      :else b)))