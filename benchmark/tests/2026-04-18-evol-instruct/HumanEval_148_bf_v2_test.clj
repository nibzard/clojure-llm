(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["Jupiter" "Mars" "Mercury" "Saturn" "Uranus" "Venus"]
         (bf "Earth" "Neptune"))))

(run-test test-variation)
