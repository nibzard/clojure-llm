(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["Earth" "Mars"] (kth-between ["Mercury" "Venus" "Earth" "Mars" "Jupiter" "Saturn" "Uranus" "Neptune"] 1 4)))
  (is (= ["Mars" "Jupiter" "Saturn"] (kth-between ["Mercury" "Venus" "Earth" "Mars" "Jupiter" "Saturn" "Uranus" "Neptune"] 6 2))))

(run-test test-variation)
