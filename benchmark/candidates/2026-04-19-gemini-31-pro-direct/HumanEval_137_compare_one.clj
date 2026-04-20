(defn compare_one [a b]
  (let [parse (fn [x]
                (if (string? x)
                  (Double/parseDouble (clojure.string/replace x #"," "."))
                  (double x)))
        val-a (parse a)
        val-b (parse b)]
    (cond
      (> val-a val-b) a
      (< val-a val-b) b
      :else nil)))