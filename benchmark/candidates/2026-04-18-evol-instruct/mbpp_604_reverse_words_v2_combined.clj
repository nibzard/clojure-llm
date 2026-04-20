(defn reverse-sections
  "Return a string with the order of whitespace-separated sections reversed.
  Preserve the original characters inside each section.

  Examples:
  (reverse-sections \"one two three\") => \"three two one\"
  (reverse-sections \"  a  b c \") => \" c b  a  \"
  (reverse-sections nil) => \"\""
  [s]
  (if (nil? s)
    ""
    (apply str (reverse (re-seq #"\s+|\S+" s)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "three two one" (reverse-sections "one two three")))
  (is (= " c b  a  " (reverse-sections "  a  b c ")))
  (is (= "" (reverse-sections nil))))

(run-test test-variation)
