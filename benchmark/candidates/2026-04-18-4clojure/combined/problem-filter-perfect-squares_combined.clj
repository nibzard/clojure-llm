(defn problem-filter-perfect-squares [a]
  (->> (clojure.string/split a #",")
       (filter (fn [s]
                 (let [n (Integer/parseInt s)
                       r (Math/sqrt n)]
                   (== r (Math/floor r)))))
       (clojure.string/join ",")))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-filter-perfect-squares
  (is (= (= (problem-filter-perfect-squares "4,5,6,7,8,9") "4,9")))
  (is (= (= (problem-filter-perfect-squares "15,16,25,36,37") "16,25,36"))))

(run-tests)
