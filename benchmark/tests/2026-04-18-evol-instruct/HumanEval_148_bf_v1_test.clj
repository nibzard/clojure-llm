(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '("Saturn" "Uranus") (bf2 ["Jupiter" "Neptune"])))
  (is (= '("Venus") (bf2 ["Earth" "Mercury"])))
  (is (= '("Venus" "Earth" "Mars" "Jupiter" "Saturn") (bf2 ["Mercury" "Uranus"])))
  (is (= '() (bf2 ["Mars"]))))

(run-test test-variation)
