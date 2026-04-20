(defn triples_sum_to_zero_sorted
  "triples_sum_to_zero_sorted takes a sorted vector of integers as input.
  It returns true if there are three distinct elements in the vector that
  sum to zero, and false otherwise.

  The vector is sorted in nondecreasing order. Your solution should take
  advantage of that fact, and it must correctly handle nil and vectors with
  fewer than three elements.

  >>> (triples_sum_to_zero_sorted [-5 -2 0 1 4 7])
  true
  >>> (triples_sum_to_zero_sorted [-3 -1 2 4 8])
  true
  >>> (triples_sum_to_zero_sorted [-4 -1 1 2 9])
  false
  >>> (triples_sum_to_zero_sorted [])
  false
  >>> (triples_sum_to_zero_sorted nil)
  false"
  [v])