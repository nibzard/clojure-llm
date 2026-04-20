(defn sort-by-second-desc
  "Return a list of vectors sorted by the second element in descending order.
  Treat nil as smaller than any numeric value.

  Examples:
  (sort-by-second-desc [[\"math\" 85] [\"science\" 92] [\"art\" 78]])
  ;; => [[\"science\" 92] [\"math\" 85] [\"art\" 78]]

  (sort-by-second-desc [[\"a\" nil] [\"b\" 10] [\"c\" 5]])
  ;; => [[\"b\" 10] [\"c\" 5] [\"a\" nil]]"
  [subjectmarks]
  (sort-by (fn [v] (let [x (second v)] (if (nil? x) Long/MIN_VALUE x))) > subjectmarks))