(defn sum-upper-name-lengths
  "Return the total length of all names in a collection that begin with an uppercase letter.

  Examples:
  (sum-upper-name-lengths [\"Alice\" \"bob\" \"Eve\"]) ;=> 8
  (sum-upper-name-lengths nil) ;=> 0
  (sum-upper-name-lengths [\"\" \"Zoe\" \"anna\"]) ;=> 3"
  [names]
  (transduce
   (comp
    (filter #(and (string? %) (not (empty? %)) (Character/isUpperCase ^char (first %))))
    (map count))
   +
   0
   (or names [])))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 8 (sum-upper-name-lengths ["Alice" "bob" "Eve"])))
  (is (= 0 (sum-upper-name-lengths nil)))
  (is (= 3 (sum-upper-name-lengths ["" "Zoe" "anna"]))))

(run-test test-variation)
