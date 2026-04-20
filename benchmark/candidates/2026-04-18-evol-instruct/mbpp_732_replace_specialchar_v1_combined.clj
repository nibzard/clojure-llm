(defn redact-specialchar
  "Replace every occurrence of spaces, commas, or dots in the input string with a colon.

  Treat nil as an empty string.

  Examples:
  (redact-specialchar \"a b,c.d\") => \"a:b:c:d\"
  (redact-specialchar nil) => \"\""
  [text]
  (-> (or text "")
      (clojure.string/replace #"[ ,\.]" ":")))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "a:b:c:d" (redact-specialchar "a b,c.d")))
  (is (= "" (redact-specialchar nil))))

(run-test test-variation)
