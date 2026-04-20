(defn below_zero
  " You're given a list of deposit and withdrawal operations on a bank account that starts with
  zero balance. Your task is to detect if at any point the balance of account fallls below zero, and
  at that point function should return true. Otherwise it should return false.
  >>> (below_zero [1 2 3])
  false
  >>> (below_zero [1 2 -4 5])
  true"
  [operations]
  (boolean
   (some neg?
         (reductions + 0 (or operations [])))))