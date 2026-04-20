(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:surface-area 12.566370614359172
          :volume 3.141592653589793}
         (cylinder-metrics 1 1)))
  (is (= {:surface-area 150.79644737231007
          :volume 113.09733552923255}
         (cylinder-metrics 3 4)))
  (is (= nil (cylinder-metrics nil 2))))

(run-test test-variation)
