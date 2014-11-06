GIT INSTRUCTION

**1. Your repo forks have been created as followed:**
Use this as "origin"

	Lam: 	git@git.assembla.com:evaluationapp.lam.git
	Quang: 	git@git.assembla.com:evaluationapp.quang.git
	Brian: 	git@git.assembla.com:evaluationapp.brian.git

This will be called "upstream"

	upstream: git@git.assembla.com:evaluationapp.git

**2. Set up git in your pc:**

	// set origin
	$git clone git@git.assembla.com:evaluationapp.quang.git (use your fork) 
	
	// add upstream if not available
	$git remote add upstream git@git.assembla.com:evaluationapp.git
	
	// set upstream
	$git remote set-url upstream git@git.assembla.com:evaluationapp.git

	// test your repos
	$git remote -v

	// you should see something like this: 

	```
		origin	git@git.assembla.com:evaluationapp.lam.git (fetch)
		origin	git@git.assembla.com:evaluationapp.lam.git (push)
		upstream	git@git.assembla.com:evaluationapp.git (fetch)
		upstream	git@git.assembla.com:evaluationapp.git (push)

								 
								 
			 ----> 	lam origin |-----> local 
			 |
			 |
	upstream --------> 	brian origin |-----> local
			 |
			 |
			 ----> 	quang origin |-----> local

	```
**3. Basic branching and merging**

	// Never work on your master branch, create a local branch based on master branch
	$git checkout -b branchName master

	// To start working on a feature, make sure your current branch is up-to-date
	$git pull origin master

	// Push it to your remote origin repo
	$git push origin branchName

	// Now you have another branch in your fork on Assembla
	// but what you work on your computer is local repo,
	// so when you finish your work on local repo, merge it with your remote origin

	// See the changes
	$git status

	// Add the files and commit
	$git add src/... 
	$git commit -m "commit message"

	// push it to your origin
	// git push = git pull + git merge
	$git push origin branchName
	
	// merge upstream with local
	$git pull upstream master

	***Now your changes will be in the branch on your origin remote repo and from here you can merge it to upstream, usually it comes with a merge request and someone would have to review it before merging it to master branch on upstream***

	local ---> origin ---> upstream




