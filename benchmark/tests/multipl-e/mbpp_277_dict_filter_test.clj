(require '[clojure.test :refer [deftest is run-test]])

(def candidate dict_filter)

(deftest test-humaneval

  (is (= (candidate {"Cierra Vega" 175 "Alden Cantrell" 180 "Kierra Gentry" 165 "Pierre Cox" 190} 170) {"Cierra Vega" 175 "Alden Cantrell" 180 "Pierre Cox" 190}))
  (is (= (candidate {"Cierra Vega" 175 "Alden Cantrell" 180 "Kierra Gentry" 165 "Pierre Cox" 190} 180) {"Alden Cantrell" 180 "Pierre Cox" 190}))
  (is (= (candidate {"Cierra Vega" 175 "Alden Cantrell" 180 "Kierra Gentry" 165 "Pierre Cox" 190} 190) {"Pierre Cox" 190}))
)

(run-test test-humaneval)